package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jamescross91 on 05/11/2015.
 */

public class Pcap {
    private float time;
    private String da;
    private String daResolved;
    private String ta;
    private String taResolved;
    private String ra;
    private String raResolved;
    private String signalDBM;
    private String signalPercent;
    private String nodeId;
    private String projectTag;

    public void init(ResultSet resultSet) {
        try {
            time = resultSet.getFloat("time");
            da = resultSet.getString("da");
            daResolved = resultSet.getString("da_resolved");
            ta = resultSet.getString("ta");
            taResolved = resultSet.getString("ta_resolved");
            ra = resultSet.getString("ra");
            raResolved = resultSet.getString("ra_resolved");
            signalDBM = resultSet.getString("signal_dbm");
            signalPercent = resultSet.getString("signal_percent");
            nodeId = resultSet.getString("node_id");
            projectTag = resultSet.getString("project_tag");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
