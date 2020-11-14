package laskoski.f.felipe.SmartInvest.Transactions.controller;


import laskoski.f.felipe.SmartInvest.Transactions.dto.TransactionDto;
import laskoski.f.felipe.SmartInvest.Transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public Page<TransactionDto> getAllTransactions(@RequestParam(required = false) String code, @RequestParam int pageNumber, @RequestParam int quantity){
        Pageable page = PageRequest.of(pageNumber, quantity);

        if(code == null)
            return TransactionDto.converter(transactionRepository.findAll(page));
        else
            return TransactionDto.converter(transactionRepository.findByAssetCode(code, page));
    }


//    @RequestMapping(path = "/ip", method = RequestMethod.GET)
//    public String getTransactions() throws Exception {
//        String ip = InetAddress.getLocalHost().getHostAddress();
//        StringBuffer sb = new StringBuffer();
//        sb.append("hello\n");
//        sb.append("IP:"+ip);
//
//        return sb.toString();
//    }

//    @RequestMapping(path = "/all", method = RequestMethod.GET)
//    public List<Transaction> getAllTransactions{
//        return Arrays.asList(new Transaction(), new Transaction());
//    }
}
