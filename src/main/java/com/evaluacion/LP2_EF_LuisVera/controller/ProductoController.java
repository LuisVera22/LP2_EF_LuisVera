package com.evaluacion.LP2_EF_LuisVera.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.CategoriaEntity;
import com.evaluacion.LP2_EF_LuisVera.model.entity.ProductoEntity;
import com.evaluacion.LP2_EF_LuisVera.model.entity.UserEntity;
import com.evaluacion.LP2_EF_LuisVera.service.CategoriaService;
import com.evaluacion.LP2_EF_LuisVera.service.ProductoService;
import com.evaluacion.LP2_EF_LuisVera.service.UserService;
import com.evaluacion.LP2_EF_LuisVera.service.impl.PdfService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductoController {
    private final UserService userService;
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final PdfService pdfService;

    @GetMapping("/lista_productos")
    public String getListadoProductos(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        String email = session.getAttribute("user").toString();
        UserEntity usuarioEncontrado = userService.findUserByEmail(email);
        model.addAttribute("nombreUsuario", usuarioEncontrado.getName());
        model.addAttribute("foto", usuarioEncontrado.getUrlImagen());
        
        List<ProductoEntity> listaProductos = productoService.buscarTodosProductos();
        model.addAttribute("productos", listaProductos);

        return "lista_productos";
    }

    @GetMapping("/generar_pdf_productos")
    public ResponseEntity<InputStreamResource> generarPdfProductos() throws IOException {
        List<ProductoEntity> listaProductos = productoService.buscarTodosProductos();

        Map<String, Object> datosPdf = new HashMap<>();
        datosPdf.put("productos", listaProductos);

        ByteArrayInputStream pdfBytes = pdfService.generarPdf("template_pdf_productos", datosPdf);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=productos.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfBytes));
    }

    @GetMapping("/editar_producto/{id}")
    public String getEditarProducto(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        String email = session.getAttribute("user").toString();
        UserEntity usuarioEncontrado = userService.findUserByEmail(email);
        model.addAttribute("nombreUsuario", usuarioEncontrado.getName());
        model.addAttribute("foto", usuarioEncontrado.getUrlImagen());

        ProductoEntity producto = productoService.buscarProductoPorId(id);
        List<CategoriaEntity> listaCategorias = categoriaService.obtenerTodasCategorias();
        model.addAttribute("categorias", listaCategorias);
        model.addAttribute("producto", producto);
        return "editar_producto";
    }

    @PostMapping("/editar_producto/{id}")
    public String postEditarProducto(@PathVariable("id") Integer id, Model model,
            @ModelAttribute("producto") ProductoEntity producto) {
        // TODO: process POST request
        try {
            productoService.actualizarProducto(id, producto);
            model.addAttribute("successMessage", "Producto actualizado con éxito.");
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("errorMessage", "Error al actualizar el producto");
        }
        return "redirect:/lista_productos";
    }

    @GetMapping("/registrar_producto")
    public String getRegistrarProducto(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        String email = session.getAttribute("user").toString();
        UserEntity usuarioEncontrado = userService.findUserByEmail(email);

        List<CategoriaEntity> listaCategorias = categoriaService.obtenerTodasCategorias();

        model.addAttribute("categorias", listaCategorias);

        model.addAttribute("nombreUsuario", usuarioEncontrado.getName());
        model.addAttribute("foto", usuarioEncontrado.getUrlImagen());

        model.addAttribute("producto", new ProductoEntity());
        return "registrar_producto";
    }

    @PostMapping("/registrar_producto")
    public String postRegistrarProducto(@ModelAttribute("producto") ProductoEntity productoEntity, Model model,
            @RequestParam("imagen") MultipartFile imagen) {
        // TODO: process POST request
        productoService.crearProducto(productoEntity, imagen);
        return "redirect:/lista_productos";
    }

    @GetMapping("/eliminar_producto/{id}")
    public String getEliminar_producto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/lista_productos";
    }

}
