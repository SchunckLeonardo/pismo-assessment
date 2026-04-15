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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Boot.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/reset-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccountShouldReturnCreatedAccount() throws Exception {
        mockMvc.perform(
                        post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "documentNumber": "52998224725"
                                        }
                                        """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account_id").isNumber())
                .andExpect(jsonPath("$.document_number").value("52998224725"));
    }

    @Test
    void retrieveAccountShouldReturnExistingAccount() throws Exception {
        Integer accountId = createAccount("52998224725");

        mockMvc.perform(get("/accounts/{accountId}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account_id").value(accountId))
                .andExpect(jsonPath("$.document_number").value("52998224725"));
    }

    @Test
    void createAccountShouldReturnConflictWhenDocumentAlreadyExists() throws Exception {
        createAccount("52998224725");

        mockMvc.perform(
                        post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "documentNumber": "52998224725"
                                        }
                                        """)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("account.already.exists"))
                .andExpect(jsonPath("$.message").value("Account with document number 52998224725 already exists."));
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
