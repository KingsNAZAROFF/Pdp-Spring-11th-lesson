package uz.pdp.pdpspring11thlesson.paylaod.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.pdpspring11thlesson.paylaod.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

}
