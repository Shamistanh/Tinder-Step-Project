package DAO;
import beans.Reaction;
import beans.User;
import db.ConnDetails;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * http://localhost:8080/hello
 * http://localhost:8080/hello?x=5
 */
public class DAOReactionSQL implements DAO<Reaction> {

    private final Connection conn;
    private final String SQL_getAll = "select * from reactions";
    private final String SQL_get    = "select * from reactions where id = ?";
    private final String SQL_put    = "insert into reactions (who, whom, reaction) values (?, ?, ?)";
    private final String SQL_delete = "delete from users where id = ?";

    public DAOReactionSQL(Connection conn) {
        this.conn = conn;
    }

    @SneakyThrows
    @Override
    public List<Reaction> getAll() {
        Class.forName(ConnDetails.dbDriver);
        Connection conn = DriverManager.getConnection(ConnDetails.url,
                ConnDetails.username,
                ConnDetails.password);
        PreparedStatement stmt = conn.prepareStatement(SQL_getAll);
        ResultSet rset = stmt.executeQuery();
        ArrayList<Reaction> data = new ArrayList<>();
        while (rset.next()) {
            Reaction s = new Reaction(
                    rset.getString("id"),
                    rset.getString("who"),
                    rset.getString("whom"),
                    rset.getString("reaction"),
                    rset.getDate("date")
            );
            data.add(s);
        }
        conn.close();
        return data;
    }

    @Override
    public List<Reaction> getBy(Predicate<Reaction> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public Optional<Reaction> get(String id) {
        PreparedStatement stmt = conn.prepareStatement(SQL_get);
        stmt.setString(1, id);
        ResultSet rset = stmt.executeQuery();
        return !rset.next() ? Optional.empty() : Optional.of(new Reaction(
                        rset.getString("id"),
                        rset.getString("who"),
                        rset.getString("whom"),
                        rset.getString("reaction"),
                        rset.getDate("date")

                )
        );
    }

    @SneakyThrows
    @Override
    public void put(Reaction reaction) {
        Class.forName(ConnDetails.dbDriver);
        Connection conn = DriverManager.getConnection(ConnDetails.url,
                ConnDetails.username,
                ConnDetails.password);
        PreparedStatement stmt = conn.prepareStatement(SQL_put);
        stmt.setString(1, reaction.getWho());
        stmt.setString(2, reaction.getWhom());
        stmt.setString(3, reaction.getReaction());
        stmt.execute();
        conn.close();
    }

    @SneakyThrows
    @Override
    public void delete(String id) {
        Class.forName(ConnDetails.dbDriver);
        Connection conn = DriverManager.getConnection(ConnDetails.url,
                ConnDetails.username,
                ConnDetails.password);
        PreparedStatement stmt = conn.prepareStatement(SQL_delete);
        stmt.setString(1, id);
        stmt.execute();
    }
}
