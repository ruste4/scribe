package te4rus.ru.scribe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import te4rus.ru.scribe.domain.User;
import te4rus.ru.scribe.service.UserService;

@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        String[] str = bindingResult.getSuppressedFields();

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            bindingResult.rejectValue("password", null, "Пароли не совпадают");
            return "registration";
        }

        boolean isUserSaved = userService.saveUser(userForm);

        if (!isUserSaved) {
            bindingResult.rejectValue("username", null,"Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }

}
