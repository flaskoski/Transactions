package laskoski.f.felipe.SmartInvest.Transactions.controller;

import laskoski.f.felipe.SmartInvest.Transactions.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("assetsPage/")
public class AssetUIController {
    @Autowired
    AssetRepository assetRepository;

    @GetMapping("/")
    public String showAssetsList(Model model) {
        model.addAttribute("assets", assetRepository.findAll());
        return "index";
    }
}
