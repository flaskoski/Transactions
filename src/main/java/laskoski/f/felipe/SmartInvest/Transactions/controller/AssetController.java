package laskoski.f.felipe.SmartInvest.Transactions.controller;

import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetDto;
import laskoski.f.felipe.SmartInvest.Transactions.dto.AssetForm;
import laskoski.f.felipe.SmartInvest.Transactions.model.Asset;
import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AssetRepository assetRepository;

    @GetMapping
    public Page<Asset> getAllAssets(@RequestParam int pageNumber, @RequestParam int quantity, String sortBy){
        Pageable page;
        if(sortBy == null || sortBy != "code")
            page = PageRequest.of(pageNumber, quantity);
        else
            page = PageRequest.of(pageNumber, quantity, Sort.Direction.ASC, sortBy);

        return assetRepository.findAll(page);
    }

    @PostMapping
    public ResponseEntity<AssetDto> addAsset(@RequestBody @Valid AssetForm assetForm, UriComponentsBuilder uriBuilder){
        Asset newAsset = assetForm.convert();
        assetRepository.save(newAsset);

        URI uri = uriBuilder.path("/assets/{id}").buildAndExpand(newAsset.getId()).toUri();
        return ResponseEntity.created(uri).body(new AssetDto(newAsset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAsset(@PathVariable Long id){
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent())
            return ResponseEntity.ok(new AssetDto(asset.get()));
        else
            return ResponseEntity.notFound().build();
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
