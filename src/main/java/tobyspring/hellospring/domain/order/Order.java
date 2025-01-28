package tobyspring.hellospring.domain.order;

import java.math.BigDecimal;

public class Order {

    private Long id;

    private String no;

    private BigDecimal total;

    public Order() {
    }

    public Order(final String no, final BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
