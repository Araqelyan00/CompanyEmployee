package companyEmployee.manager;

import companyEmployee.db.DBConnectionProvider;
import companyEmployee.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void saveCompany(Company company) {
        String sql = "INSERT INTO company(companyName, companyCountry) VALUES (?, ?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getCompanyCountry());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                company.setCompanyId(rs.getInt(1));
            }
            System.out.println("Company saved into DB.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Company getCompanyByID(int companyId) {
        String sql = "SELECT * FROM company WHERE companyId = ?";
        try (Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return getCompanyFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private Company getCompanyFromResultSet(ResultSet rs) throws SQLException {
        Company company = new Company();
        company.setCompanyId(rs.getInt("companyId"));
        company.setCompanyName(rs.getString("companyName"));
        company.setCompanyCountry(rs.getString("companyCountry"));
        return company;
    }

    public List<Company> getByCountryName(String country) {
        String sql = "SELECT * FROM company WHERE companyCountry = ?";
        List<Company> companyList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                companyList.add(getCompanyFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companyList;
    }

    public void removeByCompanyId(int companyId) {
        String sql = "DELETE FROM company WHERE companyId =" + companyId;
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCompanyById(Company company) {
        String sql = "UPDATE company SET companyName = ?, companyCountry = ? WHERE companyId = ?";
        try(Statement st = connection.createStatement()) {
            st.executeUpdate(String.format(sql, company.getCompanyName(), company.getCompanyCountry(), company.getCompanyId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
