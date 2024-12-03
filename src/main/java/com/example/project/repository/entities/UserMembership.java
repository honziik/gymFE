package com.example.project.repository.entities;

import com.example.project.repository.UserMembershipId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserMembership {
    @EmbeddedId
    private UserMembershipId id = new UserMembershipId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;

    @ManyToOne
    @MapsId("membershipId")
    @JoinColumn(name = "membership_id")
    @JsonIgnore
    private Membership membership;

    private LocalDateTime purchaseDate;
    private LocalDateTime endDate;

    public UserMembership(Users user, Membership membership, LocalDateTime purchaseDate, LocalDateTime endDate) {
        this.user = user;
        this.membership = membership;
        this.purchaseDate = purchaseDate;
        this.endDate = endDate;
        this.id = new UserMembershipId(user.getId(), membership.getId());
    }
}
