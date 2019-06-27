package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.classes.Pratos;
import com.example.demo.classes.Usuario;
import com.example.demo.service.PratosService;
import com.example.demo.service.UsuarioService;



@Controller
public class ControllerMain {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PratosService pratosService;
	
	
	
	
	///////////////////       Usuarios     ////////////////////////////////
	
	@RequestMapping("/UsuarioCadastrar")
	public ModelAndView CadastrarUsuario(@Validated Usuario usuario,BindingResult result,@RequestParam(value="senha1") String senha1) {
		
		ModelAndView mv= new ModelAndView("cadastrarUsuario");
		
		
		//System.out.println("nome "+usuario.getSenha()+"  "+senha1);
		
		
		if(!usuario.getSenha().equals(senha1)) {
			
			result.rejectValue("senha", "error.usuario", "As senhas não se coincidem");
		
		}
		
		if(usuarioService.existeCpf(usuario.getCpf())==1) {
			
		result.rejectValue("cpf", "error.usuario", "CPF já existente, cadastre outro");
		
		}
		
		if(usuarioService.existeEmail(usuario.getEmail())==1) {
			
			result.rejectValue("email", "error.usuario", "Email já existente, cadastre outro");
			
			}
		
		if(result.hasErrors()) {
			return mv;
		}
		
	mv.addObject("mensagem","Usuario cadastrado com sucesso");
		
		
	
		
		//	usuario.setTipoUser(1);
			usuarioService.cadastrarUsuario(usuario);
			mv=new ModelAndView("redirect:/login");
			return mv;
		
		//System.out.println("nome "+usuario.getNome());
		
		
	}
	
	@RequestMapping("/excluirUsuario/{id}")
	public ModelAndView excluirUsuario(@PathVariable Long id){
			
		usuarioService.excluirUsuario(id);
		ModelAndView mv=new ModelAndView("redirect:/listaUsuario");
		return mv;
	}
	///////////////////////////////////////////////////////////////////////
	
	///////////////////       Index     ////////////////////////////////
	
	@RequestMapping("/inicio")
	public String index() {
		
		return "index";
	}
	@RequestMapping("/CadUsuario")
	public ModelAndView cadastrarUsuario() {
		
		ModelAndView mv=new ModelAndView("cadastrarUsuario");
		mv.addObject("usuario",new Usuario());
		
		return mv;
	}
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/inserirPratos")
	public ModelAndView CadastrarPratos() {	
			
		ModelAndView mv=new ModelAndView("cadastrarPratos");
		mv.addObject("usuario",new Pratos());
		
		return mv;
	}
	
	@RequestMapping("/cardapio")
	public ModelAndView cardapio() {
		List<Pratos> pratos= pratosService.ListarPratos();
		ModelAndView mv=new ModelAndView("cardapio");
		mv.addObject("listaDePratos",pratos);
		
		return mv;
	}	
	
	//////////////////////////////////////////////////////////////////////
	
	///////////////////       PRATOS     ////////////////////////////////
	
	@RequestMapping("/alterarPrato")
	public ModelAndView alterarPrato() {
		
		ModelAndView mv=new ModelAndView("editarPratos");
		mv.addObject("pratos",new Pratos());
		
		return mv;
	}
	
	
	@RequestMapping("/cadastrarPratos") 
	public ModelAndView CadastroPratos(@Validated Pratos pratos,@RequestParam(value="imagem") MultipartFile img){
		
			
			
			pratosService.cadastrarPratos(pratos,img);
			ModelAndView mv=new ModelAndView("redirect:/cardapio");
			return mv;
			
	}
	
	@RequestMapping("/excluirPrato/{id}")
	public ModelAndView excluirPratos(@PathVariable Long id){
			
		pratosService.excluirPrato(id);
		ModelAndView mv=new ModelAndView("redirect:/cardapio");
		return mv;
	}
	
	@RequestMapping("/editarPrato/{id}")
	public ModelAndView EditarPratos(@PathVariable Long id){
		
		Pratos pratos=pratosService.buscarPratobyId(id);
	
		ModelAndView mv=new ModelAndView("editarPratos");
		mv.addObject("pratos",pratos);
		return mv;
		
	}
	
	////////////////////////////////////////////////////////////////////
	
	///////////////////// Carrinho /////////////////////////////////////
		
//	@RequestMapping(method = RequestMethod.GET)
//	public String index(ModelMap modelMap) {
//		ProductModel productModel = new ProductModel();
//		modelMap.put("products", productModel.findAll());
//		return "product/index";
//	}

}
