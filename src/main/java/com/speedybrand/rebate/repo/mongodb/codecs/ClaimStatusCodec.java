package com.speedybrand.rebate.repo.mongodb.codecs;

import com.speedybrand.rebate.pojo.ClaimStatus;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class ClaimStatusCodec implements Codec<ClaimStatus> {

    @Override
    public ClaimStatus decode(BsonReader reader, DecoderContext decoderContext) {
        return ClaimStatus.from(reader.readInt32());
    }

    @Override
    public void encode(BsonWriter writer, ClaimStatus value, EncoderContext encoderContext) {
        writer.writeInt32(value.getId());
    }

    @Override
    public Class<ClaimStatus> getEncoderClass() {
        return ClaimStatus.class;
    }
}
