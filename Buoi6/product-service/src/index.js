require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const productRoutes = require('./routes/product.routes');

const app = express();
app.use(express.json());
app.use('/products', productRoutes);

mongoose.connect(process.env.MONGO_URL)
  .then(() => {
    console.log('Connected to MongoDB');
    app.listen(process.env.PORT, () => {
      console.log(`Product service running on port ${process.env.PORT}`);
    });
  })
  .catch(err => console.error(err));
