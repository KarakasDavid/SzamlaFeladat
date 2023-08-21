package com.example.security.Controller;

import com.example.security.Model.Person;
import com.example.security.Model.Receipt;
import com.example.security.Repository.ReceiptRepository;
import com.example.security.Service.ReceiptService;
import com.example.security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Validated
public class ReceiptController {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private  UserService userService;

    @GetMapping("/receipts")
    public String listReceipts(Model model) {
        List<Receipt> receipts = receiptRepository.findAll();
        Person currentUser = userService.getCurrentUser();
        model.addAttribute("receipts", receipts);
        model.addAttribute("currentUser", currentUser);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
        model.addAttribute("roles", roles);

        return "receiptList";
    }
    @GetMapping("/addReceipt")
    public String showAddReceiptForm() {
        return "addReceipt";
    }

    @PostMapping("/addReceipt")
    public String addReceipt(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String item_name,
            @RequestParam String comment,
            @RequestParam(name = "start_date") LocalDate start,
            @RequestParam(name = "end_date")  LocalDate end) {

        if (receiptRepository.findByName(name) == null) {
            Receipt receipt = new Receipt();
            receipt.setName(name);
            receipt.setPrice(price);
            receipt.setEnd_date(end);
            receipt.setComment(comment);
            receipt.setStart_date(start);
            receipt.setItem_name(item_name);

            receiptRepository.save(receipt);
            return "redirect:/receipts";
        }
        return "redirect:/receipts";
    }


    @GetMapping("/receiptDetail/{id}")
    public String showReceiptDetail(@PathVariable Long id, Model model) {

        Receipt receipt = receiptService.findById(id);
        model.addAttribute("receipt", receipt);

        return "receiptDetail";
    }
}