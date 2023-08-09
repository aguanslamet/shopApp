package com.shopApp.model;

import com.shopApp.service.transaction.impl.EPaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@NoArgsConstructor
@Getter
@Table(name = "transaction")
public class Transaction extends AuditableCart<Long>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_amount", updatable = false)
    private Long totalAmount = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private EPaymentStatus paymentStatus = EPaymentStatus.FAILED;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "paid_at")
    private Date paidAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_at")
    private Date expiredAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cancel_at")
    private Date cancelAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "valid_at")
    private Date validAt;
}
