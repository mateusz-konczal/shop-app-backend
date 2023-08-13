package pl.webapp.shop.product.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import pl.webapp.shop.common.model.Product;

import java.util.ArrayList;
import java.util.List;

final class ProductSpecification {

    private ProductSpecification() {
    }

    static Specification<Product> createProductSpecification(String phrase) {
        return (root, query, builder) -> builder.and(createPredicates(phrase, root, builder).toArray(Predicate[]::new));
    }

    static Specification<Product> createProductSpecificationForAscEndPrice(String phrase) {
        return (root, query, builder) -> {
            query.orderBy(builder.asc(builder.coalesce(root.get("salePrice"), root.get("price"))));
            return builder.and(createPredicates(phrase, root, builder).toArray(Predicate[]::new));
        };
    }

    static Specification<Product> createProductSpecificationForDescEndPrice(String phrase) {
        return (root, query, builder) -> {
            query.orderBy(builder.desc(builder.coalesce(root.get("salePrice"), root.get("price"))));
            return builder.and(createPredicates(phrase, root, builder).toArray(Predicate[]::new));
        };
    }

    private static List<Predicate> createPredicates(String phrase, Root<Product> root, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.and(builder.equal(root.get("enabled"), true)));

        if (StringUtils.hasText(phrase)) {
            String pattern = "%" + phrase.toLowerCase() + "%";
            Predicate productName = builder.like(builder.lower(root.get("name")), pattern);
            Predicate productDescription = builder.like(builder.lower(root.get("description")), pattern);
            predicates.add(builder.or(productName, productDescription));
        }

        return predicates;
    }
}
