package it.iad.sigeba2;

import it.iad.sigeba2.repository.MovimentoCCRepository;
import it.iad.sigeba2.service.MovimentoCCService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovimentoCCTest {
    
  Random random = new Random();

    @Autowired
    MovimentoCCService movimentoccService;

    @Autowired
    MovimentoCCRepository movimentoccRepository; 
    
//    @Test
}
