package laskoski.f.felipe.SmartInvest.Transactions.controller;


import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetDto;
import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetForm;
import laskoski.f.felipe.SmartInvest.Transactions.dto.TransactionDto;
import laskoski.f.felipe.SmartInvest.Transactions.dto.TransactionForm;
import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import laskoski.f.felipe.SmartInvest.Transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AssetRepository assetRepository;

//    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    @GetMapping
    @Cacheable(value = "all-transactions")
    public Page<TransactionDto> getAllTransactions(@RequestParam(required = false) String code, @PageableDefault(sort="asset", direction = Sort.Direction.ASC) Pageable page){

        if(code == null)
            return TransactionDto.converter(transactionRepository.findAll(page));
        else
            return TransactionDto.converter(transactionRepository.findByAssetCode(code, page));
    }
    @PostMapping
    @CacheEvict(value = "all-transactions")
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid TransactionForm form, UriComponentsBuilder uriBuilder){
        Transaction newTransaction = form.convert(assetRepository);
        transactionRepository.save(newTransaction);

        //build URI of the new item
        URI uri = uriBuilder.path("/transactions/{id}").buildAndExpand(newTransaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransactionDto(newTransaction));
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
