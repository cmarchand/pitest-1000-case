package com.oxiane.formation.devoxx22.refacto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;

public class FactureTest {
    private Calendar YESTERDAY;
    private Calendar TOMORROW;

    @BeforeEach
    public void beforeEach() {
        YESTERDAY = Calendar.getInstance();
        YESTERDAY.set(Calendar.DAY_OF_YEAR, YESTERDAY.get(Calendar.DAY_OF_YEAR)-1);
        TOMORROW = Calendar.getInstance();
        TOMORROW.set(Calendar.DAY_OF_YEAR, TOMORROW.get(Calendar.DAY_OF_YEAR)+1);
    }

    @Test
    public void given_vistamboire_price10_facture_ttc_should_be_12() {
        // Given
        Vistamboire vistamboire = new Vistamboire(BigDecimal.TEN,BigDecimal.valueOf(0.2),YESTERDAY, TOMORROW);
        Adresse adresse = new Adresse(null, "11 rue Albert", null, "76600", "Le Havre", "France");
        Client client = new Client("Marchand", "Christophe", adresse);
        Facture facture = new Facture(client, Calendar.getInstance());
        BigDecimal expected = BigDecimal.valueOf(12.0);

        // when
        facture.calculate(vistamboire);

        // then
        Assertions.assertThat(facture.getTotalTTC()).isEqualTo(expected);
    }
}
