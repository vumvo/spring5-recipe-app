package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipese());
//        Optional<Category> category = categoryRepository.findByDescription("Vietnamese");
//        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Cup");
//        Optional<Recipe> recipe = recipeRepository.findById();
//        System.out.println("Cat ID is :" + category.get().getId());
//        System.out.println("UOM ID is :" + uom.get().getId());

        return "index";
    }
}
