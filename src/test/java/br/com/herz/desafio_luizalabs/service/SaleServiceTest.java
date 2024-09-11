package br.com.herz.desafio_luizalabs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import br.com.herz.desafio_luizalabs.exception.InvalidFileException;
import br.com.herz.desafio_luizalabs.exception.InvalidHeaderException;
import br.com.herz.desafio_luizalabs.repository.SaleRepository;

public class SaleServiceTest {

    private static final String FILENAME = "example_input.tab";

    @InjectMocks
    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadSalesValidFile() throws Exception {
        File file = new File(FILENAME);
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        MockMultipartFile mockFile = new MockMultipartFile(FILENAME, fileBytes);

        BigDecimal total = saleService.uploadSales(mockFile);

        assertNotNull(total);
        assertEquals(new BigDecimal("95.00"), total);
        verify(saleRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testUploadSalesInvalidHeader() {
        String invalidHeader = "invalid header";
        String content = invalidHeader + "\n";
        MockMultipartFile file = new MockMultipartFile("file", "sales.txt", "text/plain", content.getBytes());

        assertThrows(InvalidHeaderException.class, () -> saleService.uploadSales(file));
    }

    @Test
    public void testUploadSalesInvalidFile() {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        assertThrows(InvalidFileException.class, () -> saleService.uploadSales(emptyFile));
    }

}
