package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Participant;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.ParticipantService;

@Path("/api/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController implements IEntityController<Entry> {

    @Inject
    EntryService entryService;

    @Inject
    ParticipantService participantService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries", description = "")
    public List<Entry> list() {
        return entryService.list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry create(Entry entry) {
        if (!entry.getCheckIn().isBefore(entry.getCheckOut())) {
            throw new WebApplicationException("CheckIn DateTime must be before CheckOut DateTime", Response.Status.BAD_REQUEST);
        }

        Entry created = entryService.create(entry);

        for (Participant participant : created.getParticipants()) {
            participant.setEntry(created);
            participantService.create(participant);
        }

        return created;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Gets the entry with the given id", description = "Searches for the entry with the specified id and returns the Entry object")
    public Entry get(@PathParam("id") Long id) {
        return entryService.get(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Tries to delete the entry", description = "Deletes the entry corresponding the given entry id")
    public void delete(@PathParam("id") Long id) {
        entryService.delete(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates the given entry", description = "Merges the given entry with the entry that has the same id in the database")
    public Entry update(Entry entry) {
        return entryService.update(entry);
    }
}
