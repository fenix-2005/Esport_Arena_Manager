package com.EsportManager.Notification_Service.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Audit {

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    /**
     * Este mÃƒÂ©todo se ejecuta automaticamente una vez que el objeto es creado
     */
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDate.now();
    }

    /**
     * Este mÃƒÂ©todo se ejecuta automaticamente cuando se realiza cualquier actu
     * lizaciÃƒÂ³n del objeto que se encuentra asociado.
     */
    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDate.now();
    }
}




