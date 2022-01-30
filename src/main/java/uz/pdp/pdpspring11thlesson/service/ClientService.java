package uz.pdp.pdpspring11thlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Client;
import uz.pdp.pdpspring11thlesson.paylaod.Result;
import uz.pdp.pdpspring11thlesson.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client){

        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists){
            return new Result("Ushbu telefon raqam oldinham ro'yxatdan o'tkazilgan",false);
        }
        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(newClient);
        return new Result("Client DB ga saqlandi",true);
    }

    public Page<Client> getClients(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return clientRepository.findAll(pageable);
    }
    public Client getClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseGet(Client::new);
    }

    public Result editeClient(Integer id,Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()){
            return new Result("Bunday client topilmadi",false);
        }

        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists){
            return new Result("Ushbu telefon raqam oldinham ro'yxatdan o'tkazilgan",false);
        }
        Client editingClient = optionalClient.get();
        editingClient.setPhoneNumber(client.getPhoneNumber());
        editingClient.setName(client.getName());
        clientRepository.save(editingClient);
        return  new Result("Client ma'lumotlari o'zgartirildi",true);

    }
    public Result deleteClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            clientRepository.deleteById(id);
            return new Result("Client DB dan o'chirildi",true);
        }
        return new Result("Bunday client topilmadi",false);
    }

}
