/*
 * Copyright Red Hat, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Red Hat trademarks are not licensed under GPLv3. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package com.redhat.swatch.export;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/api/swatch-subscription-sync")
public class ExportTestingInternalResource {

  private final ExportProperties exportProperties;

  public ExportTestingInternalResource(ExportProperties exportProperties) {
    this.exportProperties = exportProperties;
  }

  @POST
  @Path("/internal/export")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response exportData(Object data) {

    putMessageOnExportKafkaTopic(data);

    return Response.status(Status.ACCEPTED).entity("Export request queued up").build();
  }

  private void putMessageOnExportKafkaTopic(Object data) {

    log.info("Put a test message on the {} topic", exportProperties.getKafkaTopic());
  }
}
