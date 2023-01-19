package com.sf.superherosightings.dao;

import com.sf.superherosightings.dto.Organization;

import java.util.List;

public interface OrganizationDao {
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization Organization);
    void updateOrganization(Organization Organization);
    void deleteOrganizationById(int id);
}
