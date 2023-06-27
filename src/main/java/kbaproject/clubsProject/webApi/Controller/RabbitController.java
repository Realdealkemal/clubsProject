package kbaproject.clubsProject.webApi.Controller;

import kbaproject.clubsProject.Business.concretes.RabbitTransferServıceImpl;
import kbaproject.clubsProject.Business.requests.TransferRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/rabbit"})
@NoArgsConstructor
@AllArgsConstructor
public class RabbitController {
    @Autowired
    RabbitTransferServıceImpl rabbitTransferServıce;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferPlayer(@RequestBody TransferRequest transferRequest){
        rabbitTransferServıce.transferPlayer(transferRequest);
        return ResponseEntity.ok("Oyuncu transfer oldu");
    }

}
