package ch.heigvd.gamification.api;

import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.database.dao.ApplicationRepository;
import ch.heigvd.gamification.database.dao.BadgeRepository;
import ch.heigvd.gamification.utils.JSONParser;
import ch.heigvd.gamification.utils.JWTutils;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        //TODO PASSE EN LONG DANS LA BDD OU PASSER EN INT (a discuter)
        long tmp = badgeId;
        try{
            badgeRepository.delete((int)tmp);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @Override
    public ResponseEntity<Badge> findBadge(@ApiParam(value = "ID of badge to fetch", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        long tmp = badgeId;
        try{
            ch.heigvd.gamification.database.model.Badge badgeDB = badgeRepository.findOne((int)tmp);
            if(badgeDB == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            Badge badge = new Badge();
            badge.setName(badgeDB.getName());
            return new ResponseEntity<>(badge, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<Badge>> findBadges(@ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        List<ch.heigvd.gamification.database.model.Badge> list = badgeRepository.findAll();
        List<Badge> toReturn = new LinkedList<>();
        Iterator i = list.iterator();
        while(i.hasNext())
        {
            Badge tmp = new Badge();
            tmp.setName(((ch.heigvd.gamification.database.model.Badge)i.next()).getName());
            toReturn.add(tmp);
        }
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateBadge(@ApiParam(value = "Badge object to add to the store", required = true) @RequestBody Badge badge, @ApiParam(value = "Id of the badge that needs to be updated", required = true) @PathVariable("badgeId") Long badgeId, @ApiParam(value = "token to be passed as a header", required = true) @RequestHeader(value = "token", required = true) String token) {
        String name = JWTutils.getAppNameInToken(token);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        long tmp = badgeId;
        try {
            ch.heigvd.gamification.database.model.Badge badgeDB = badgeRepository.findOne((int) tmp);
            if (badgeDB == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
