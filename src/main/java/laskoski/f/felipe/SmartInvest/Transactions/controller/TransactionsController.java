package laskoski.f.felipe.SmartInvest.Transactions.controller;


import laskoski.f.felipe.SmartInvest.Transactions.dto.*;
import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import laskoski.f.felipe.SmartInvest.Transactions.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AssetRepository assetRepository;

//    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    @GetMapping
    @Cacheable(value = "all-transactions")
    public Page<TransactionDto> getAllTransactions(@RequestParam String username, @RequestParam(required = false) String code, @PageableDefault(sort="asset", direction = Sort.Direction.ASC) Pageable page){
        if(code == null)
            return TransactionDto.converter(transactionRepository.findByUsername(username, page));
        else
            return TransactionDto.converter(transactionRepository.findByUsernameAndAssetCode(username, code, page));
    }
    @PostMapping
    @CacheEvict(value = "all-transactions", allEntries = true)
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid TransactionForm form, UriComponentsBuilder uriBuilder){
        Transaction newTransaction = form.convert(assetRepository);
        transactionRepository.save(newTransaction);

        //build URI of the new item
        URI uri = uriBuilder.path("/transactions/{id}").buildAndExpand(newTransaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransactionDto(newTransaction));
    }
    @PostMapping(path = "/many")
    @CacheEvict(value = "all-transactions", allEntries = true)
    public ResponseEntity<TransactionDto> addMultipleTransactions(@RequestBody @Valid List<TransactionForm> formList, UriComponentsBuilder uriBuilder){
        StringBuffer sb = new StringBuffer("Transactions IDs added: ");
        formList.forEach(form -> {
            Transaction newTransaction = form.convert(assetRepository);
            transactionRepository.save(newTransaction);
            sb.append(newTransaction.getId()+",");
        });
        logger.info(sb.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "all-transactions", allEntries = true)
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody @Valid TransactionForm transactionForm){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent()) {
            Transaction updatedTransaction = transactionForm.update(transaction.get(), assetRepository);
            transactionRepository.save(updatedTransaction);
            return ResponseEntity.ok(new TransactionDto(updatedTransaction));
        }
        else
            return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @CacheEvict(value = "all-transactions", allEntries = true)
    public ResponseEntity<?> removeTransaction(@PathVariable Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent()) {
            transactionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/filter")
    public Page<TransactionDto> getTransactionsWithFilter(@RequestParam String username, @RequestBody @Valid TransactionFiltersDto removedOptions, @PageableDefault(sort="asset", direction = Sort.Direction.ASC) Pageable pageable){
        Page<TransactionDto> page;
        if(removedOptions.getAsset() == null || removedOptions.getAsset().size() == 0)
            if(removedOptions.getType() == null  || removedOptions.getType().size() == 0)
                page = TransactionDto.converter(transactionRepository.findByUsername(username, pageable));
            else page = TransactionDto.converter(transactionRepository.findByUsernameAndTypeNotIn(username, removedOptions.getType(), pageable));
        else if(removedOptions.getType() == null  || removedOptions.getType().size() == 0)
            page = TransactionDto.converter(transactionRepository.findByUsernameAndAssetCodeNotIn(username, removedOptions.getAsset(), pageable));
        else page = TransactionDto.converter(transactionRepository.findByUsernameAndAssetCodeNotInAndTypeNotIn(username, removedOptions.getAsset(), removedOptions.getType(), pageable));

            removedOptions.setType(new ArrayList<>());

        return page;
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
