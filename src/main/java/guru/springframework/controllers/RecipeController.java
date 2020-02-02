package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"recipe/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        Recipe recipeReturned = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipeReturned);
        return "recipe/show";
    }

    @RequestMapping({"recipe/new"})
    public String newRecipe(Model model) {
        RecipeCommand command = new RecipeCommand();
        model.addAttribute("recipe", command);
        return "recipe/recipeform";
    }

    @RequestMapping({"recipe/{id}/update"})
    public String updateRecipe(@PathVariable String id, Model model) {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", command);
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+ savedRecipeCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping({"recipe/{id}/delete"})
    public String deleteRecipeById(@PathVariable String id) {
        log.debug("Deleing id: " + id);
        recipeService.deletedById(Long.valueOf(id));
        return "redirect:/";
    }
}
