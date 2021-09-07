package com.midas.epkorea.domain.manager;

import com.midas.epkorea.dto.ManagerRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Manager implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int no;
    private Timestamp registrationDate;

    private String id;
    private String name;
    private String belong;
    private String phoneNumber;
    private String password;

    private boolean pmUpsSts;
    private boolean pmEss;
    private boolean pmCooling;
    private boolean pmLighting;
    private boolean pmRailroad;

    private boolean cmUpsSts;
    private boolean cmEss;
    private boolean cmCooling;
    private boolean cmLighting;
    private boolean cmRailroad;

    private String role;


    public void createManagerByManagerRequest(ManagerRequestDto managerRequestDto){

        this.id= managerRequestDto.getId();
        this.name=managerRequestDto.getName();
        this.belong= managerRequestDto.getBelong();
        this.phoneNumber=managerRequestDto.getPhoneNumber();
        this.password=managerRequestDto.getPassword();

        this.pmUpsSts = managerRequestDto.isPmUpsSts();
        this.pmEss = managerRequestDto.isPmEss();
        this.pmCooling = managerRequestDto.isPmCooling();
        this.pmLighting = managerRequestDto.isPmLighting();
        this.pmRailroad = managerRequestDto.isPmRailroad();

        this.cmUpsSts = managerRequestDto.isCmUpsSts();
        this.cmEss = managerRequestDto.isCmEss();

        this.cmCooling = managerRequestDto.isCmCooling();
        this.cmLighting = managerRequestDto.isCmLighting();
        this.cmRailroad = managerRequestDto.isCmRailroad();

        this.role=managerRequestDto.getRole();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(this.role));
        return roles;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
