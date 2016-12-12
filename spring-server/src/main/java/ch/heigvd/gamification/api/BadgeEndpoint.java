package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.BadgeRepository;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lux on 30.11.16.
 */
@RestController
public class BadgeEndpoint implements BadgesApi {
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public ResponseEntity<Void> addBadge(@ApiParam(value = "Badge object to add to the store", required = true) @RequestBody Badge badge, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        ch.heigvd.gamification.database.model.Badge newBadge = new ch.heigvd.gamification.database.model.Badge();
        newBadge.setName(badge.getName());
        try{
            newBadge.setApplication(applicationRepository.findByName(name));
            badgeRepository.save(newBadge);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "Id of the badge that needs to be deleted", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<Badge> findBadge(@ApiParam(value = "ID of badge to fetch", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<List<Badge>> findBadges(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateBadge(@ApiParam(value = "Badge object to add to the store", required = true) @RequestBody Badge badge, @ApiParam(value = "Id of the badge that needs to be updated", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        return null;
    }
}
