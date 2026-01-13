package com.migestion.cashflow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "registro_caja")
public class RegistroCaja {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime openedAt;
        private LocalDateTime closedAt;

        private Double initialAmount;
        private Double finalAmount;

        @OneToMany(mappedBy = "registroCaja")
        private List<FlujoCajaMovimiento> transactions;

        private boolean open;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public LocalDateTime getOpenedAt() {
                return openedAt;
        }

        public void setOpenedAt(LocalDateTime openedAt) {
                this.openedAt = openedAt;
        }

        public LocalDateTime getClosedAt() {
                return closedAt;
        }

        public void setClosedAt(LocalDateTime closedAt) {
                this.closedAt = closedAt;
        }

        public Double getInitialAmount() {
                return initialAmount;
        }

        public void setInitialAmount(Double initialAmount) {
                this.initialAmount = initialAmount;
        }

        public Double getFinalAmount() {
                return finalAmount;
        }

        public void setFinalAmount(Double finalAmount) {
                this.finalAmount = finalAmount;
        }

        public List<FlujoCajaMovimiento> getTransactions() {
                return transactions;
        }

        public void setTransactions(List<FlujoCajaMovimiento> transactions) {
                this.transactions = transactions;
        }

        public boolean isOpen() {
                return open;
        }

        public void setOpen(boolean open) {
                this.open = open;
        }


}
