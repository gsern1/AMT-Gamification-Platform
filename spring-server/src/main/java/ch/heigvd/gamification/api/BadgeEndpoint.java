package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.BadgeWithLocation;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.BadgeRepository;
import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Badges endoint. Implements CRUD actions for this endpoint.
 */
@RestController
public class BadgeEndpoint implements BadgesApi {
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * Add a badge to your application.
     *
     * @param badge: the badge to be added.
     * @param token: the token.
     * @return
     *      403 if your token is invalid.
     *      422 if the badge name is null or empty
     *      201 otherwise
     */
    @Override
    public ResponseEntity<Void> addBadge(@ApiParam(value = "Badge object to add to the store", required = true) @RequestBody Badge badge, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(badge.getName() == null || badge.getName().equals(""))
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        ch.heigvd.gamification.database.model.Badge newBadge = new ch.heigvd.gamification.database.model.Badge();
        newBadge.setName(badge.getName());
        try{
            newBadge.setApplication(application);
            badgeRepository.save(newBadge);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/badges/" + newBadge.getId());
            return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /**
     * Delete this badge
     *
     * @param badgeId: the id of the badge to be deleted.
     * @param token: the token
     *
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this badge.
     *      422 if the database fails to delete it.
     *      204 otherwise
     */
    @Override
    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "Id of the badge that needs to be deleted", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.Badge badge = badgeRepository.findOne(badgeId);
        if(badge == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try{
            badgeRepository.delete(badge);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    /**
     * find a badge with the given badge id.
     *
     * @param badgeId: the id of the badge to be retrieved
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this badge.
     *      401 if you don't own this badge.
     *      422 if the database fails to read it.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<Badge> findBadge(@ApiParam(value = "ID of badge to fetch", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.Badge badgeDB = badgeRepository.findOne(badgeId);
        if(badgeDB == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(application.getId() != badgeDB.getApplication().getId())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try{
            Badge badge = new Badge();
            badge.setName(badgeDB.getName());
            return new ResponseEntity<>(badge, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Get a list of the badges of this application.
     *
     * @param token: the token
     * @return
     *      403 if your token is invalid.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<List<BadgeWithLocation>> findBadges(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {

        String name = JWTutils.getAppNameInToken(token);
        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);
        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<ch.heigvd.gamification.database.model.Badge> list = badgeRepository.findByApplication(application);
        List<BadgeWithLocation> toReturn = new LinkedList<>();
        Iterator i = list.iterator();
        while(i.hasNext())
        {
            BadgeWithLocation tmp = new BadgeWithLocation();
            ch.heigvd.gamification.database.model.Badge b = (ch.heigvd.gamification.database.model.Badge)i.next();
            tmp.setName(b.getName());
            tmp.setLocation("/badges/" + b.getId());
            toReturn.add(tmp);
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    /**
     * Updates this badge.
     *
     * @param badge: the new badge
     * @param badgeId: the badge to be updated
     * @param token: the toke
     * @return
     *      403 if your token is invalid.
     *      404 if the system has not found this badge.
     *      401 if you don't own this badge.
     *      422 if the database fails to update it.
     *      200 otherwise
     */
    @Override
    public ResponseEntity<Void> updateBadge(@ApiParam(value = "Badge object to add to the store", required = true) @RequestBody Badge badge, @ApiParam(value = "Id of the badge that needs to be updated", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);

        if(name == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Application application = applicationRepository.findByName(name);

        if(application == null){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ch.heigvd.gamification.database.model.Badge badgeDB = badgeRepository.findOne(badgeId);
        if (badgeDB == null || badge.getName() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(application.getId() != badgeDB.getApplication().getId())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            if (badgeDB == null || badge.getName() == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            badgeDB.setName(badge.getName());
            badgeRepository.save(badgeDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
