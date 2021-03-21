package laskoski.f.felipe.SmartInvest.Transactions.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static URI pathToNewAsset;

    @Test
    public void testAA_GetFilteredTransactionsWithout2AssetsAnd1Type() throws Exception {
        URI uri = new URI("/transactions/filter?username=flaskoski&page=0&size=30");
        String removedOptions = "{\"asset\": [\"PETR4\",\"VALE3\",\"SAPR11\"], \"type\": [\"BUY\"]}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(removedOptions)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("TEST11")));
    }
    @Test
    public void testAB_GetFilteredTransactionsWithAssetFilterNull() throws Exception {
        URI uri = new URI("/transactions/filter?username=flaskoski&page=0&size=30");
        String removedOptions = "{\"type\": [\"SELL\"]}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(removedOptions)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("TEST11")));
    }
    @Test
    public void testAC_GetFilteredTransactionsWithAssetFilterEmpty() throws Exception {
        URI uri = new URI("/transactions/filter?username=flaskoski&page=0&size=30");
        String removedOptions = "{\"asset\": [\"PETR4\"]}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(removedOptions)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("TEST11")));
    }
    @Test
    public void testAC_GetFilteredTransactionsWithFiltersNull() throws Exception {
        URI uri = new URI("/transactions/filter?username=flaskoski&page=0&size=30");
        String removedOptions = "{}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(removedOptions)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andReturn();
//                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("TEST11")));
    }
}