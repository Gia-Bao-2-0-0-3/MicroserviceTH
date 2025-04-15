const router = require('express').Router();

const controller = require('../controllers/order.controller');

router.get('/', controller.get);
router.get('/:id', controller.getOne);
router.post('/', controller.post);
router.put('/update/:id', controller.put); 
router.delete('/delete/:id', controller.delete);

module.exports = router;