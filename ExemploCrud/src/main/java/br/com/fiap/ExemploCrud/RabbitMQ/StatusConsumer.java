package br.com.fiap.ExemploCrud.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ExemploCrud.service.UsuarioService;

@Service
public class StatusConsumer {
	
	@Autowired
	UsuarioService service;
	
    @RabbitListener(queues = "statusQueue")
    public void receiveStatus(String status) {
    	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	 service.atualizarStatus(status);
    	 
        // Implemente a lógica para atualizar o status no banco de dados
        System.out.println("Status atualizado: " + status);
    }
}
