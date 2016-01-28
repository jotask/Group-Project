package com.github.jotask.groupproject.connection.dao;

import com.github.jotask.groupproject.connection.DataBase;
import com.github.jotask.groupproject.model.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Team Dao for retrieve information from the database for objects
 *
 * @author Jose Vives.
 *
 * @version 1.0
 */
public class TeamDao extends Dao {

    /**
     * Constructor for a team
     *
     * @param db
     *      The actual DataBase instance
     * @param conn
     *      The connection instance
     */
    public TeamDao(DataBase db, Connection conn) {
        super(db, conn);
    }

    /**
     * Get all team on the database
     *
     * @return
     *      An ArrayList of all Teams
     *
     */
    public ArrayList<Team> getAllTeam(){

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
                // Nothing to do
            }
        }
        return teams;
    }

    /**
     * Convert one resultset to a team
     *
     * @param rs
     *      The resultset were we want retrieve the information
     *
     * @return
     *      The team created
     *
     * @throws SQLException
     *      The SQL exception that holds the error message
     */
    private Team createTeam(ResultSet rs) throws SQLException {

        int id = rs.getInt("team_id");
        String name = rs.getString("name_id");

        return new Team(id, name);
    }

}
