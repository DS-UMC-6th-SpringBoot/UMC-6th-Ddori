package umc.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  private Float score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_id")
  private Region region;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Mission> missionList = new ArrayList<>();

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Review> reviewList = new ArrayList<>();
}