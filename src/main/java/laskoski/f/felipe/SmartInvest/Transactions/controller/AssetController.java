package laskoski.f.felipe.SmartInvest.Transactions.controller;

import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetDto;
import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetForm;
import laskoski.f.felipe.SmartInvest.Transactions.dto.TransactionDto;
import laskoski.f.felipe.SmartInvest.Transactions.dto.TransactionForm;
import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.model.Transaction;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/assets")
public class AssetController {

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    AssetRepository assetRepository;

    @GetMapping
    public Page<Asset> getAllAssets(@RequestParam int page, @RequestParam int size, @RequestParam String username, String sortBy){
        Pageable pageable;
        if(sortBy == null || !sortBy.equals("code"))
            pageable = PageRequest.of(page, size);
        else
            pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortBy);

        return assetRepository.findByUsername(username, pageable);
    }

    @PostMapping
    public ResponseEntity<AssetDto> addAsset(@RequestBody @Valid AssetForm assetForm, UriComponentsBuilder uriBuilder){
        Asset newAsset = assetForm.convert();
        assetRepository.save(newAsset);

        URI uri = uriBuilder.path("/assets/{id}").buildAndExpand(newAsset.getId()).toUri();
        return ResponseEntity.created(uri).body(new AssetDto(newAsset));
    }

    @PostMapping(path = "/many")
    public ResponseEntity<TransactionDto> addMultipleAssets(@RequestBody @Valid List<AssetForm> formList, UriComponentsBuilder uriBuilder){
        StringBuffer sb = new StringBuffer("Assets IDs added: ");
        formList.forEach(form -> {
            Asset newAsset = form.convert();
            assetRepository.save(newAsset);
            sb.append(newAsset.getId()+",");
        });
        logger.info(sb.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAsset(@PathVariable Long id){
        Optional<Asset> asset = assetRepository.findById(id);
        return asset.map(value -> ResponseEntity.ok(new AssetDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AssetDto> updateAsset(@PathVariable Long id, @RequestBody @Valid AssetForm assetForm){
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()) {
            Asset updatedAsset = assetForm.update(asset.get());
            return ResponseEntity.ok(new AssetDto(updatedAsset));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAsset(@PathVariable Long id){
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()) {
            assetRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }
}
