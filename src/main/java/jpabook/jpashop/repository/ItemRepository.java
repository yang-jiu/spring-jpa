package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    //상품 저장
    public void save(Item item){
        if(item.getId() == null){  // 신규 등록
            em.persist(item);
        }else{
            em.merge(item);  // 업데이트
        }
    }

    //상품 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //전체 상품 목록 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
