package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Location;
import ch.zli.m223.punchclock.service.LocationService;

@Path("/api/locations")
@Tag(name = "Locations", description = "Handling of locations")
public class LocationController implements IEntityController<Location> {
    @Inject
    private LocationService locationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all locations", description = "")
    public List<Location> list() {
        return locationService.list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Location", description = "The given Location is added into the database, assuming the provided data is correct")
    public Location create(Location location) {
        if (location.getZip() <= 9999) {
            throw new WebApplicationException("ZIP cannot be greater than 9999", Response.Status.BAD_REQUEST);
        }

        return locationService.create(location);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Gets the Location with the given id", description = "Searches for the Location with the specified id and returns the object")
    public Location get(@PathParam("id") Long id) {
        return locationService.get(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Tries to delete the location", description = "Deletes the location corresponding the given id")
    public void delete(@PathParam("id") Long id) {
        locationService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates the given location", description = "Merges the given location with the location that has the same id in the database")
    public Location update(Location location) {
        return locationService.update(location);
    }
}
