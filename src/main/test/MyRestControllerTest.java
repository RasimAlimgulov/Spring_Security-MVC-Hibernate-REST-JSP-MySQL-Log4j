import dancebase.springmvc.security.rest.controller.MyRestController;
import dancebase.springmvc.security.rest.entities.Dancer;
import dancebase.springmvc.security.rest.service.DancerService;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MyRestControllerTest {
    @Mock
    private DancerService dancerService;

    @InjectMocks
    private MyRestController restController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(restController).build();
    }

    private Logger logger= LoggerFactory.getLogger(MyRestControllerTest.class);
    @Test
    public void getAllDancers() throws Exception {
        List<Dancer> dancers=new ArrayList<>();
        Dancer dancer=new Dancer("Rasim","Adad",1998,"asdasdad");
        dancers.add(dancer);
        when(dancerService.getAllDancers()).thenReturn(dancers);
        mockMvc.perform(get("/dancers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rasim"))
                .andExpect(jsonPath("$[0].surname").value("Adad"))
                .andExpect(jsonPath("$[0].year").value(1998))
                .andExpect(jsonPath("$[0].danceClub").value("asdasdad"));
    logger.info("SomeMessage");
    }

//    @Test
    public void getDancerById(){
        MyRestController controller=new MyRestController();
        Dancer dancer=controller.getDancerById(1);
        Dancer checkDancer=new Dancer("Rasim","Alimgulov",1998,"Elite");
        checkDancer.setId(1);
        Assertions.assertEquals(dancer,checkDancer,"Not same objects");
    }

}
