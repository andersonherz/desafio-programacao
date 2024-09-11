package br.com.herz.desafio_luizalabs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.herz.desafio_luizalabs.entity.Sale;
import br.com.herz.desafio_luizalabs.exception.InvalidFileException;
import br.com.herz.desafio_luizalabs.exception.InvalidHeaderException;
import br.com.herz.desafio_luizalabs.repository.SaleRepository;

@Service
public class SaleService {

    private static final String EXPECTED_HEADER = "purchaser name\titem description\titem price\tpurchase count\tmerchant address\tmerchant name";

    @Autowired
    private SaleRepository repository;

    public BigDecimal uploadSales(MultipartFile file)
            throws IOException, InvalidHeaderException, InvalidFileException, InterruptedException, ExecutionException {
        validateFile(file);
        List<Sale> salesImported = parseFileSingleThread(file);
		return sumarizeSales(salesImported);
	}

    private List<Sale> parseFileSingleThread(MultipartFile file) throws IOException, InvalidHeaderException {
        var init = System.currentTimeMillis();
        List<Sale> importedSales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            validateHeader(reader.readLine());
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                var sale = new Sale();
                sale.setPurchaserName(data[0]);
                sale.setItemDescription(data[1]);
                sale.setItemPrice(BigDecimal.valueOf(Double.parseDouble(data[2])));
                sale.setPurchaseCount(BigDecimal.valueOf(Double.parseDouble(data[3])));
                sale.setMerchantAddress(data[4]);
                sale.setMerchantName(data[5]);

                importedSales.add(sale);
            }
        }

        repository.saveAll(importedSales);

        System.err.println("Tempo processamento: " + (System.currentTimeMillis() - init));
        return importedSales;
    }


    private void validateFile(MultipartFile file) throws InvalidFileException {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException();
        }
    }

    private void validateHeader(String line) throws InvalidHeaderException {
        if (line == null || !line.equalsIgnoreCase(EXPECTED_HEADER)) {
            throw new InvalidHeaderException(EXPECTED_HEADER, line);
        }
    }

    private BigDecimal sumarizeSales(List<Sale> salesImported) {
		return salesImported.stream() //
				.map(element -> element.getItemPrice().multiply(element.getPurchaseCount())) //
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
