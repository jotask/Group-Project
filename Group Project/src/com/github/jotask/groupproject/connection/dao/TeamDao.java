package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Jose Vives on 01/12/2015.
 *
 * @author Jose Vives.
 * @since 01/12/2015
 */
public class TeamDao extends  DAO{
    /**
     * @param db   The actual DataBase instance
     * @param conn
     */
    public TeamDao(DataBase db, Connection conn) {
        super(db, conn);
    }

    public ArrayList<Team> getAllTeam(){
        // TODO get all tasks from connection
        ArrayList<Team> teams = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM team";
            rs = stm.executeQuery(sql);

            while(rs.next()){
                Team team = createTeam(rs);
                teams.add(team);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                close(stm, rs);
            } catch (SQLException e) {
                // TODO nothing to do
                e.printStackTrace();
            }
        }
        return teams;
    }

    private Team createTeam(ResultSet rs) throws SQLException {

        int id = rs.getInt("team_id");
        String name = rs.getString("name_id");

        return new Team(id, name);
    }

}
