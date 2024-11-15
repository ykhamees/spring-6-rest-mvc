package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private UUID id;
    @Version
    private String version;

    private String customerName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
