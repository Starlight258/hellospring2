package tobyspring.hellospring.data;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import tobyspring.hellospring.domain.order.Order;
import tobyspring.hellospring.domain.order.OrderRepository;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(final DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    void initDB() {
        if (tableExists("orders")) {
            return;
        }
        jdbcClient.sql("""
                create table orders (id bigint not null, no varchar(255), total numeric(38,2), primary key (id));
                alter table if exists orders drop constraint if exists UK_43egxxciqr9ncgmxbdx2avi8n;
                alter table if exists orders add constraint UK_43egxxciqr9ncgmxbdx2avi8n unique (no);
                create sequence orders_SEQ start with 1 increment by 50;
                """).update();
    }

    private boolean tableExists(String tableName) {
        try {
            jdbcClient.sql("SELECT 1 FROM " + tableName + " WHERE 1 = 0")
                    .query(Long.class)
                    .list();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void save(final Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ")
                .query(Long.class)
                .single();
        order.setId(id);

        jdbcClient.sql("insert into orders (no, total,id) values (?,?,?)")
                .params(order.getNo(), order.getTotal(), order.getId())
                .update();
    }
}
