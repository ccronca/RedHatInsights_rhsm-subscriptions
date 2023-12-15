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

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ExportProperties.class)
@Slf4j
public class ExportRequestListener {

  private final ExportProperties exportProperties;
  private final RbacValidator rbacValidator;
  private final ExportServiceNotifier exportServiceNotifier;

  public ExportRequestListener(
      ExportProperties exportProperties,
      RbacValidator rbacValidator,
      ExportServiceNotifier exportServiceNotifier) {
    this.exportProperties = exportProperties;
    this.rbacValidator = rbacValidator;
    this.exportServiceNotifier = exportServiceNotifier;
  }

  public void consumeMessageFromKafkaTopic() {

    log.info("Consuming from topic: {}", exportProperties.getKafkaTopic());

    rbacValidator.isVerifiedByRbac();

    String data = getTheData();

    exportServiceNotifier.postToExportService(data);
  }

  private String getTheData() {

    // Dynamically injected Consumer function depending on what type of export is requested.  Either
    // Subscription/Tally controller that will fetch the data (and probably save it somewhere) and
    // return here to then be sent back off to the export service.
    // Need to avoid making this swatch-export dependent on the monolith or swatch-core

    return "SubscriptionExportHandler.fetchTheThings";
  }
}
