package br.com.pismo.customerassessment.integration;

import br.com.pismo.customerassessment.Boot;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Boot.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/reset-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTransactionShouldStorePositiveAmountForPayment() throws Exception {
        Integer accountId = createAccount("52998224725");

        mockMvc.perform(
                        post("/transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "account_id": %d,
                                          "operation_type_id": 4,
                                          "amount": 123.45
                                        }
                                        """.formatted(accountId))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transaction_id").isNumber())
                .andExpect(jsonPath("$.account_id").value(accountId))
                .andExpect(jsonPath("$.operation_type_id").value(4))
                .andExpect(jsonPath("$.amount").value(123.45));
    }

    @Test
    void createTransactionShouldStoreNegativeAmountForPurchase() throws Exception {
        Integer accountId = createAccount("52998224725");

        mockMvc.perform(
                        post("/transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "account_id": %d,
                                          "operation_type_id": 1,
                                          "amount": 123.45
                                        }
                                        """.formatted(accountId))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account_id").value(accountId))
                .andExpect(jsonPath("$.operation_type_id").value(1))
                .andExpect(jsonPath("$.amount").value(-123.45));
    }

    @Test
    void createTransactionShouldReturnNotFoundWhenOperationTypeDoesNotExist() throws Exception {
        Integer accountId = createAccount("52998224725");

        mockMvc.perform(
                        post("/transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "account_id": %d,
                                          "operation_type_id": 9,
                                          "amount": 123.45
                                        }
                                        """.formatted(accountId))
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("operation.type.not.exists"))
                .andExpect(jsonPath("$.message").value("Operation type with id 9 does not exist."));
    }

    @Test
    void createTransactionShouldReturnBadRequestWhenAmountIsInvalid() throws Exception {
        Integer accountId = createAccount("52998224725");

        mockMvc.perform(
                        post("/transactions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "account_id": %d,
                                          "operation_type_id": 4,
                                          "amount": 0
                                        }
                                        """.formatted(accountId))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("method.argument.not.valid"))
                .andExpect(jsonPath("$.message").value("Amount must be greater than zero"));
    }

    private Integer createAccount(String documentNumber) throws Exception {
        MvcResult result = mockMvc.perform(
                        post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "documentNumber": "%s"
                                        }
                                        """.formatted(documentNumber))
                )
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode responseJson = objectMapper.readTree(result.getResponse().getContentAsString());
        return responseJson.get("account_id").asInt();
    }

}
