import dancebase.springmvc.security.rest.controller.MyRestController;
import dancebase.springmvc.security.rest.entities.Dancer;
import dancebase.springmvc.security.rest.service.DancerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyRestControllerTest {
    @Mock
    List list;
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
    @Order(1)
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
    logger.info("Первый тест завершён");
    }

enum Names{
        Rasim,Damir,Renat
}
    @Timeout(value = 3,unit = TimeUnit.SECONDS)
    @ParameterizedTest
    @Order(2)
    @ValueSource(ints = {1,2,3})
    public void getDancerById(Integer param) throws Exception {
        Dancer checkDancer=new Dancer("Rasim","Alimgulov",1998,"Elite");
        when(dancerService.getDancerById(param)).thenReturn(checkDancer);
        mockMvc.perform(get("/dancers/"+param)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rasim"))
                .andExpect(jsonPath("$.surname").value("Alimgulov"));
        logger.info("Второй тест завершён");
    }

    @ParameterizedTest
    @EnumSource(Names.class)
    public void someTest(Names name){
        Assertions.assertEquals(Names.Damir,name,"Not same Name");
    }

    @ParameterizedTest
    @MethodSource("method")
    @Order(3)
    void method2(Integer x){
        System.out.println(x);
    }

    @ParameterizedTest
    @CsvSource({
            "alex, 11, Программист, Работает",
            "brian, 35, Тестировщик, Работает",
            "charles, 40, Менеджер, Пинает"
    })
    void testWithCsvSource(String name, int age, String occupation, String status) {

    }

    static Stream<Integer> method(){
        return Stream.of(4,7,9);
    }
    @Test
    @DisplayName("Mock test")
    void myMockTest() {
        list.add("adada");
        list.add(123123);
        System.out.println(list.get(0));
        List<String> myList = Mockito.spy(new ArrayList<String>());
        myList.add("jkhkj");
        myList.add("fdsadfs");
        System.out.println(myList.toString());

        Mockito.doReturn(10).when(list).size();
        Assertions.assertEquals(list.size(), 10);

        Mockito.doThrow(EnumConstantNotPresentException.class).when(list).get(anyInt());
        Assertions.assertThrows(EnumConstantNotPresentException.class, () -> {
            list.get(4);
        }, "Полученно исключение");


        Mockito.doAnswer(x -> {
            int a = x.getArgument(0);
            a = a * a;
            return a;
        }).when(myList).get(anyInt());
        Assertions.assertEquals(myList.get(5), 25);

        Mockito.verify(myList).get(5);
        Mockito.verify(list, never()).get(6);
        Mockito.verify(list, times(2)).get(anyInt());
//        Mockito.verifyNoInteractions(list);
        list.clear();
        InOrder inOrder = Mockito.inOrder(list);
        inOrder.verify(list).add("adada");
        inOrder.verify(list).get(anyInt());
        inOrder.verify(list).size();
        inOrder.verify(list).clear();

        Mockito.when(myList.contains(6)).thenThrow(ExceptionInInitializerError.class);
        Assertions.assertThrows(ExceptionInInitializerError.class, () -> myList.contains(6));

        try (MockedStatic<A> mockedStatic = Mockito.mockStatic(A.class)) {
            mockedStatic.when(A::print).thenAnswer(invocation-> {System.out.println("Заменил метод void в классе A"); return null;});
            A.print();
        }

    }
}
