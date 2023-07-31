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
package org.candlepin.subscriptions.subscription;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@DirtiesContext
@ActiveProfiles({"capacity-ingress", "kafka-queue"})
public class SubscriptionSyncControllerBenchmarkTests {

  @Autowired SubscriptionSyncController subscriptionSyncController;

  private static final String ORG_ID = "123";

  @Test
  void sanityCheck() {

    assertTrue(true);
  }

  @Test
  void testOriginal() {
    subscriptionSyncController.reconcileSubscriptionsWithSubscriptionServiceOld(ORG_ID, false);
  }

  @Test
  void testUpdated() {

    subscriptionSyncController.reconcileSubscriptionsWithSubscriptionServiceRefactored(
        ORG_ID, false);
  }

  @Test
  void testWithFlushing() {

    subscriptionSyncController.reconcileSubscriptionsWithSubscriptionServiceRefactoredFlushing(
        ORG_ID, false);
  }
}
