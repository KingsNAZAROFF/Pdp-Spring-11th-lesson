package uz.pdp.pdpspring11thlesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Client;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClientController(Client client){
        return clientService.addClient(client);
    }

    @GetMapping
    public Page<Client> getAllController(@RequestParam int page){
        return clientService.getClients(page);
    }
    @GetMapping("/{id}")
    public Client getClientController(@PathVariable Integer id){
        return clientService.getClient(id);
    }
    @PutMapping("/{id}")
    public  Result editClientController(@PathVariable Integer id,@RequestBody Client client){
        return clientService.editeClient(id,client);
    }

    @DeleteMapping("/{id}")
    public Result deleteClientController(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }
}
