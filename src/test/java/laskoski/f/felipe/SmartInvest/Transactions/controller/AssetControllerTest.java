package laskoski.f.felipe.SmartInvest.Transactions.controller;

import static org.hamcrest.Matchers.*;
import org.junit.*;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static URI pathToNewAsset;

    @Test
    public void testA_CreateAsset() throws Exception {
        URI uri = new URI("/assets");
        String json = "{\"code\": \"TEST11\", \"username\": \"test_user\", \"type\": \"Stocks\"}";

//        create new asset
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();

//        get created item path
        String pathString = result.getResponse().getHeader("Location");
        if(pathString == null)
            Assert.fail();
        pathToNewAsset = new URI(pathString);
    }

    @Test
    public void testB_getAsset() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(pathToNewAsset)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is("TEST11")));
    }

    @Test
    public void testC_editAsset() throws Exception {
        String json = "{\"code\": \"TEST12\", \"username\": \"test_user\", \"type\": \"Stocks\"}";

        mockMvc.perform(MockMvcRequestBuilders.put(pathToNewAsset)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
    }

    @Test
    public void testZ_deleteAsset() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(pathToNewAsset))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
