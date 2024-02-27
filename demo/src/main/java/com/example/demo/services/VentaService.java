package com.example.demo.services;



import com.example.demo.dto.DetalleFacturaDTO;
import com.example.demo.models.Cliente;
import com.example.demo.models.Producto;
import com.example.demo.models.Venta;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repositorio;
    @Autowired

    private ProductoService ProductoService;
    @Autowired

    private ClienteService clienteService;

    public Venta addSale(DetalleFacturaDTO detalleFactura) {
        Optional<Cliente> foundClient = clienteService.getClientById(detalleFactura.getClientId());
        if (foundClient.isEmpty()) {
            return null;
        }
        List<Long> productIds = detalleFactura.getProductIds();
        List<Producto> foundProducts;
        if (detalleFactura.getProductIds() != null && !detalleFactura.getProductIds().isEmpty()) {
            // List<Long> productIdsIterable = saleRequest.getProductIds();
            foundProducts = ProductoService.getProductsByIds(productIds);
        } else {
            return null;
        }

        List<Producto> saleProducts  = new ArrayList<>();;

        for (Long productId : productIds) {
            for (Producto producto : foundProducts) {
                if (producto.getId() == productId) {
                    saleProducts.add(producto);
                }
            }
        }

        double saleTotal = saleProducts.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        LocalDateTime saleDate = getCurrentDateTime();
        Venta sale = new Venta();
        sale.setCliente(foundClient.get());
        sale.setProductos(saleProducts);
        sale.setDate(saleDate);
        sale.setQuantity(detalleFactura.getProductIds().size());
        sale.setMontoTotal(saleTotal);
        return repositorio.save(sale);
    }

    /*public ResponseEntity<String> addSell(Venta venta){
        repositorio.save(venta);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");

    }*/

    public Optional<Venta> getSaleById(Long id) {
        return repositorio.findById(id);
    }
    public List<Venta> listSell(){
        return repositorio.findAll();
    }

    public ResponseEntity<String> updateSell(Long id, Venta venta){
        Venta updateSell = repositorio.findById(id).get();
        updateSell.setMontoTotal(venta.getMontoTotal());
        updateSell.setCliente(venta.getCliente());
        repositorio.save(updateSell);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

    public ResponseEntity<String> deleteSell(Long id){
        Venta deleteSell = repositorio.findById(id).get();
        repositorio.delete(deleteSell);
        return ResponseEntity.status(200).body("200 -> Operacion Satisfactoria!\n");
    }

    public LocalDateTime getCurrentDateTime() {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            LocalDateTime currentDateTime = LocalDateTime.parse(json.substring(7, json.length() - 2), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return currentDateTime;
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }
}
